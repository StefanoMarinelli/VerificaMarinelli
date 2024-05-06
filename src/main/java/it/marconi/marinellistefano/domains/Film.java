package it.marconi.marinellistefano.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    
    String codice;
    String titolo;
    String genere;
    int anno;
    String voto;
}
