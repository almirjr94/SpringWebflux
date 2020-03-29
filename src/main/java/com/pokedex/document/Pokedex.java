package com.pokedex.document;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Pokedex {

  @Id
  private String id;
  private String nome;

  public Pokedex(){
    this.id = UUID.randomUUID().toString();
  }

  public Pokedex(String nome){
    this();
    this.nome = nome;
  }

}
