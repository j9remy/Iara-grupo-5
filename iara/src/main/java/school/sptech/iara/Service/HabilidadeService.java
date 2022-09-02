package school.sptech.iara.Service;

import com.github.javafaker.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.iara.model.Categoria;
import school.sptech.iara.model.Habilidade;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.repository.HabilidadeRepository;
import school.sptech.iara.repository.PrestadorRepository;
import school.sptech.iara.request.PrestadorHabilidadeRequest;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadeService {
    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @Autowired
    private PrestadorRepository prestadorRepository;
    @Autowired
    private CategoriaService categoriaService;

    public ResponseEntity<List<Habilidade>> buscaTodasHabilidades(){
        List<Habilidade> habilidades = habilidadeRepository.findAll();
        if (!habilidades.isEmpty()){
            return ResponseEntity.status(200).body(habilidades);
        }
        return ResponseEntity.status(204).build();
    }

    public ResponseEntity<Void> associarPrestador(PrestadorHabilidadeRequest req){
        Optional<Prestador> prestadorOptional = prestadorRepository.findById(req.getIdPrestador());
        if (prestadorOptional.isPresent()){
            Prestador prestador = prestadorOptional.get();

            Categoria categoria;
            if(!categoriaService.categoriaExiste(req.getCategoria())){
                categoria = new Categoria(req.getCategoria());
                categoriaService.cadastrarCategoria(categoria);
            }else {
                ResponseEntity<Categoria> categoriaResponseEntity = categoriaService.buscaCategoria(req.getCategoria());
                if (categoriaResponseEntity.getStatusCodeValue() != 200)
                    return ResponseEntity.status(400).build();
                categoria = categoriaResponseEntity.getBody();
            }
            if (!prestador.habilidadeExiste(new Habilidade(categoria, req.getDescricao()))){
                if (habilidadeRepository.existsByCategoriaAndDescricao(categoria, req.getDescricao())){
                    Optional<Habilidade> habilidadeOptional = habilidadeRepository.findByCategoriaAndDescricao(categoria, req.getDescricao());
                    if (habilidadeOptional.isPresent()){
                        Habilidade habilidade = habilidadeOptional.get();
                        prestador.addHabilidade(habilidade);
                        prestadorRepository.save(prestador);
                        return ResponseEntity.status(201).build();
                    }
                    return ResponseEntity.status(400).build();
                }
                Habilidade habilidade = new Habilidade(categoria, req.getDescricao());
                habilidadeRepository.save(habilidade);
                prestador.addHabilidade(habilidade);
                prestadorRepository.save(prestador);
                return ResponseEntity.status(201).build();
            }
        }
        return ResponseEntity.status(400).build();
    }
}
