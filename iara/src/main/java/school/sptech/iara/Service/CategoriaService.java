package school.sptech.iara.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.iara.model.Categoria;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.repository.CategoriaRepository;
import school.sptech.iara.repository.PrestadorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PrestadorRepository prestadorRepository;

    public Boolean categoriaExiste(Categoria categoria){
        List<Categoria> categoriasExistentes = buscaTodasCategorias().getBody();
        assert categoriasExistentes != null;
        if (categoriasExistentes == null || categoriasExistentes.isEmpty())
            return false;
        for (Categoria cat: categoriasExistentes) {
            if (cat.getCategoria().equalsIgnoreCase(categoria.getCategoria())){
                return true;
            }
        }
        return false;
    }
    public Boolean categoriaExiste(String nmCategoria){
        List<Categoria> categoriasExistentes = buscaTodasCategorias().getBody();
        assert categoriasExistentes != null;
        if (categoriasExistentes == null || categoriasExistentes.isEmpty())
            return false;
        for (Categoria cat: categoriasExistentes) {
            if (cat.getCategoria().equalsIgnoreCase(nmCategoria)){
                return true;
            }
        }
        return false;
    }
    public Boolean categoriaExiste(Integer idCategoria){
        ResponseEntity<Categoria> response = buscaCategoria(idCategoria);
        if (response.getStatusCodeValue() == 200 && response.getBody() != null){
            Categoria categoria = response.getBody();
            List<Categoria> categoriasExistentes = buscaTodasCategorias().getBody();
            assert categoriasExistentes != null;
            for (Categoria cat: categoriasExistentes) {
                if (cat.getCategoria().equalsIgnoreCase(categoria.getCategoria())){
                    return true;
                }
            }
        }
        return false;
    }
    public ResponseEntity<List<Categoria>> buscaTodasCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        if (!categorias.isEmpty()){
            return ResponseEntity.status(200).body(categorias);
        }
        return ResponseEntity.status(204).build();
    }

    public ResponseEntity<Categoria> buscaCategoria(Integer idCategoria){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);
        if (categoriaOptional.isPresent()){
            Categoria categoria = categoriaOptional.get();
            return ResponseEntity.status(200).body(categoria);
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<Categoria> buscaCategoria(String nmCategoria){
        Optional<Categoria> categoriaOptional = categoriaRepository.findByCategoria(nmCategoria);
        if (categoriaOptional.isPresent()){
            Categoria categoria = categoriaOptional.get();
            return ResponseEntity.status(200).body(categoria);
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<List<Prestador>> buscaPrestadoresPorCategoria(Integer idCategoria){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(idCategoria);
        if (categoriaOptional.isPresent()){
            Categoria categoria = categoriaOptional.get();
            List<Prestador> prestadores = prestadorRepository.findAllByHabilidades_Categoria(categoria);
            if (!prestadores.isEmpty())
                return ResponseEntity.status(200).body(prestadores);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(400).build();
    }

    public ResponseEntity<Void> cadastrarCategoria(Categoria categoria){
        if (categoriaExiste(categoria))
            return ResponseEntity.status(409).build();
        categoriaRepository.save(categoria);
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<Void> updateImagem(Integer idCategoria, byte[] foto){
        if (categoriaExiste(idCategoria)){
            Categoria categoria = categoriaRepository.findById(idCategoria).get();
            categoria.setImagem(foto);
            try{
                categoriaRepository.save(categoria);
                return ResponseEntity.status(201).build();
            }catch (Exception e){
                return ResponseEntity.status(400).build();
            }
        }
        return ResponseEntity.status(400).build();
    }

    public ResponseEntity<Void> deleteFoto(Integer idCategoria){
        if (categoriaExiste(idCategoria)){
            Categoria categoria = categoriaRepository.findById(idCategoria).get();
            if (categoria.getImagem() != null){
                categoria.setImagem(null);
                categoriaRepository.save(categoria);
                return ResponseEntity.status(202).build();
            }
        }
        return ResponseEntity.status(400).build();
    }


}
