package school.sptech.iara.controller;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Foto;
import school.sptech.iara.model.Portifolio;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.repository.FotoRepository;
import school.sptech.iara.repository.PortifolioRepository;
import school.sptech.iara.repository.PrestadorRepository;
import school.sptech.iara.request.FotoRequest;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/portifolio")
@CrossOrigin
public class PortifolioController {
    @Autowired
    private PortifolioRepository portifolioRepository;
    @Autowired
    private PrestadorRepository prestadorRepository;
    @Autowired
    private FotoRepository fotoRepository;

    @PostMapping(value = "/{idPrestador}", consumes = "image/jpeg")
    public ResponseEntity<Void> postFoto(@PathVariable Integer idPrestador,
                                         @RequestBody byte[] foto){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(idPrestador);
        if (prestadorOptional.isPresent()){


            Foto fotoNova = new Foto(foto, LocalDate.now());

            fotoRepository.save(fotoNova);
            Portifolio portifolio = portifolioRepository.findByPrestador(prestadorOptional.get());
            portifolio.adicionarFoto(fotoNova);
            portifolioRepository.save(portifolio);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(400).build();
    }
}
