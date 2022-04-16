package br.edu.femass.livraria.dao;

import br.edu.femass.livraria.model.Academico;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AcademicoDao implements Dao<Academico>{

    private final XStream xstream = new XStream();
    private static List<Academico> academicos = new ArrayList<>();

    public void update(){
        String xml = xstream.toXML(academicos);
        try {
            FileWriter fw = new FileWriter("autores.xml");
            fw.write(xml);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravar(Academico objeto){
        academicos.add(objeto);
        update();
    }

    @Override
    public List<Academico> listar(){
        xstream.alias("Autor", Academico.class);
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypes(new Class[] {Academico.class});
        academicos = (List<Academico>) xstream.fromXML(new File("academicos.xml"));
        return academicos;
    }

    @Override
    public void excluir(Academico objeto) throws Exception {
        academicos.remove(objeto);
        update();
    }

}
