package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.Service.CategoriaService;
import school.sptech.iara.model.Categoria;
import school.sptech.iara.model.Prestador;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias(){
        ResponseEntity<List<Categoria>> res = categoriaService.buscaTodasCategorias();
        if (res.getStatusCodeValue() == 200)
            return ResponseEntity.status(res.getStatusCodeValue()).body(res.getBody());
        return ResponseEntity.status(res.getStatusCodeValue()).build();
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable Integer idCategoria){
        ResponseEntity<Categoria> res = categoriaService.buscaCategoria(idCategoria);
        if (res.getStatusCodeValue() == 200)
            return ResponseEntity.status(res.getStatusCodeValue()).body(res.getBody());
        return ResponseEntity.status(res.getStatusCodeValue()).build();
    }

    @GetMapping("/prestador/{idcategoria}")
    public ResponseEntity<List<Prestador>> getAllPrestadores(@PathVariable Integer idcategoria){
        ResponseEntity<List<Prestador>> res = categoriaService.buscaPrestadoresPorCategoria(idcategoria);
        if (res.getStatusCodeValue() == 200)
            return ResponseEntity.status(res.getStatusCodeValue()).body(res.getBody());
        return ResponseEntity.status(res.getStatusCodeValue()).build();
    }

    @PostMapping("/{nmCategoria}")
    public ResponseEntity<Void> postCategoria(@PathVariable String nmCategoria){
        ResponseEntity<Void> res = categoriaService.cadastrarCategoria(new Categoria(nmCategoria));
        return ResponseEntity.status(res.getStatusCodeValue()).build();
    }

    @PatchMapping(value = "/foto/{idCategoria}", consumes = "image/jpeg")
    public ResponseEntity<Void> patchFoto(@PathVariable Integer idCategoria,@RequestBody byte[] foto){
        return ResponseEntity.status(categoriaService.updateImagem(idCategoria, foto).getStatusCodeValue()).build();
    }

    @DeleteMapping("/foto/{idCategoria}")
    public ResponseEntity<Void> deleteFoto(@PathVariable Integer idCategoria){
        ResponseEntity<Void> res = categoriaService.deleteFoto(idCategoria);
        return ResponseEntity.status(res.getStatusCodeValue()).build();
    }

}
