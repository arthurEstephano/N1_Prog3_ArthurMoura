package br.edu.femass.livraria.dao;

import br.edu.femass.livraria.model.Autor;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

    public class AutorDao implements Dao<Autor> {

        private final XStream xstream = new XStream();
        private static List<Autor> autores = new ArrayList<>();

        public void update(){
            String xml = xstream.toXML(autores);
            try {
                FileWriter fw = new FileWriter("autores.xml");
                fw.write(xml);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void gravar(Autor objeto){
            autores.add(objeto);
            update();
        }

        @Override
        public List<Autor> listar(){
            xstream.alias("Autor", Autor.class);
            xstream.addPermission(NoTypePermission.NONE);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
            xstream.allowTypeHierarchy(Collection.class);
            xstream.allowTypes(new Class[] {Autor.class});
            autores = (List<Autor>) xstream.fromXML(new File("autores.xml"));
            return autores;
        }

        @Override
        public void excluir(Autor objeto){
            autores.remove(objeto);
            update();
        }
    }


