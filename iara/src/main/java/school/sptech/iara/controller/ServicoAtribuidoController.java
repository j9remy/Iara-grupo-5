package school.sptech.iara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.iara.model.Cliente;
import school.sptech.iara.model.Prestador;
import school.sptech.iara.model.Servico;
import school.sptech.iara.model.ServicoAtribuido;
import school.sptech.iara.repository.PrestadorRepository;
import school.sptech.iara.repository.ServicoRepository;
import school.sptech.iara.request.PrestadorServicoRequest;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/servico-atribuido")
public class ServicoAtribuidoController {


}
