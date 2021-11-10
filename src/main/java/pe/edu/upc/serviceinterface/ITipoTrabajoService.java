package pe.edu.upc.serviceinterface;

import java.util.List;


import pe.edu.upc.entities.TipoTrabajo;

public interface ITipoTrabajoService {
public Integer insert(TipoTrabajo tipo);
List<TipoTrabajo> list();
TipoTrabajo listarId(int idtipo);
public void delete(int idtipo);
}
