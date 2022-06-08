package school.sptech.iara.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Foto;
import school.sptech.iara.model.Portifolio;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.repository.FotoRepository;
import school.sptech.iara.repository.PortifolioRepository;
import school.sptech.iara.repository.PrestadorRepository;

import java.util.List;
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna uma única instância de foto"),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada"),
            @ApiResponse(responseCode = "400", description = "Prestador ou Portifolio não encontrado")
    })
    @GetMapping("/{idPrestador}/{idFoto}")
    public ResponseEntity<Foto> getPortifolio(@PathVariable Integer idPrestador,
                                                @PathVariable Integer idFoto){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(idPrestador);
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            Optional<Portifolio> portifolioOptional = portifolioRepository.findByPrestador(prestador);
            if (portifolioOptional.isPresent()){
                Portifolio portifolio = portifolioOptional.get();
                Optional<Foto> fotoOptional = fotoRepository.findByPortifolioAndId(portifolio, idFoto);
                if (fotoOptional.isPresent()){
                    Foto foto = fotoOptional.get();
                    return ResponseEntity.status(200).body(foto);
                }
                return ResponseEntity.status(404).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foi retornada uma lista de instâncias de foto"),
            @ApiResponse(responseCode = "204", description = "A busca foi feita, mas não houve retorno de fotos"),
            @ApiResponse(responseCode = "400", description = "Prestador ou portifólio não encontrado")
    })
    @GetMapping("/{idPrestador}")
    public ResponseEntity<List<Foto>> getPortifolio(@PathVariable Integer idPrestador){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(idPrestador);
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();
            Optional<Portifolio> portifolioOptional = portifolioRepository.findByPrestador(prestador);
            if (portifolioOptional.isPresent()){
                Portifolio portifolio = portifolioOptional.get();
                List<Foto> fotos = fotoRepository.findAllByPortifolioOrderByDataAsc(portifolio);
                if (!fotos.isEmpty()){
                    return ResponseEntity.status(200).body(fotos);
                }
                return ResponseEntity.status(204).body(fotos);
            }
        }
        return ResponseEntity.status(400).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Foto cadastrada no portifolio com sucesso"),
            @ApiResponse(responseCode = "400", description = "Prestador ou portifólio não encontrado")
    })
    @PostMapping(value = "/{idPrestador}", consumes = "image/jpeg")
    public ResponseEntity<Void> postFoto(@PathVariable Integer idPrestador,
                                         @RequestBody byte[] foto){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(idPrestador);
        if (prestadorOptional.isPresent()){
            Optional<Portifolio> portifolioOptional = portifolioRepository.findByPrestador(prestadorOptional.get());
            if (portifolioOptional.isPresent()){
                Portifolio portifolio = portifolioOptional.get();
                Foto fotoNova = new Foto(foto, portifolio);
                fotoRepository.save(fotoNova);
                portifolioRepository.save(portifolio);
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Remoção da imagem feita com sucesso"),
            @ApiResponse(responseCode = "204", description = "Foto na qual houve a tentativa de exclusão não existe"),
            @ApiResponse(responseCode = "400", description = "Prestador ou portifólio não encontrado")
    })
    @DeleteMapping("/{idPrestador}/{idFoto}")
    public ResponseEntity<Void> deleteFoto(@PathVariable Integer idPrestador,
                                           @PathVariable Integer idFoto){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(idPrestador);
        if (prestadorOptional.isPresent()) {
            Optional<Portifolio> portifolioOptional = portifolioRepository.findByPrestador(prestadorOptional.get());
            if (portifolioOptional.isPresent()){
                Portifolio portifolio = portifolioOptional.get();
                Optional<Foto> fotoOptional = fotoRepository.findByPortifolioAndId(portifolio, idFoto);
                if (fotoOptional.isPresent()){
                    Foto foto = fotoOptional.get();
                    fotoRepository.delete(foto);
                    return ResponseEntity.status(202).build();
                }
                return ResponseEntity.status(204).build();
            }
        }
        return ResponseEntity.status(400).build();
    }
}
