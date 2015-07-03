package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by Rayan on 30/06/2015.
 */


@DatabaseTable(tableName = "bairro")
public class Bairro {

    @DatabaseField(generatedId=true)
    private int id;
    @DatabaseField
    private String nome;

    private List<Estabelecimento> estabelecimentoList;
}
