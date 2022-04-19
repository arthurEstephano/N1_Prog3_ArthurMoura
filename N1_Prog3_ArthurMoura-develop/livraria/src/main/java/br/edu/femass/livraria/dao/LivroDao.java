package br.edu.femass.livraria.dao;

import br.edu.femass.livraria.model.GeneroLivro;
import br.edu.femass.livraria.model.Livro;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class LivroDao implements Dao<Livro>{
    private final XStream xstream = new XStream();
    private static List<Livro> livros = new ArrayList<>();

    public void update(){
        String xml = xstream.toXML(livros);
        try {
            FileWriter fw = new FileWriter("livros.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravar(Livro objeto){
        livros.add(objeto);
        update();
    }

    @Override
    public List<Livro> listar(){
        xstream.alias("Livro", Livro.class);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypes(new Class[] {Livro.class, GeneroLivro.class});
        livros = (List<Livro>) xstream.fromXML(new File("livros.xml"));
        return livros;
    }

    @Override
    public void excluir(Livro objeto){
        livros.remove(objeto);
        update();
    }

}
