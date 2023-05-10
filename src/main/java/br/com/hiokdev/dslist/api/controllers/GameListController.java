package br.com.hiokdev.dslist.api.controllers;

import br.com.hiokdev.dslist.api.dto.GameListDTO;
import br.com.hiokdev.dslist.domain.services.GameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

  private final GameListService gameListService;

  @Autowired
  public GameListController(GameListService gameListService) {
    this.gameListService = gameListService;
  }

  @GetMapping
  public List<GameListDTO> findAll() {
    var list = gameListService.findAll();

    return list.stream().map(GameListDTO::new).toList();
  }

}
