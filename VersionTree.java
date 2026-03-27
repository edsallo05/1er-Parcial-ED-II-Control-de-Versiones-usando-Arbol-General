import java.util.SortedMap;

public class VersionTree {
    private Version raiz;
    private Version version_actual;
    private int contador_id = 1;

    public VersionTree(String contenido){
        this.raiz = new Version(contador_id++,contenido);
        this.version_actual = raiz;
    }

    public VersionTree(){
        this.raiz = null;
    }
    private void recorrerVersionTree(Version actual){

        if(actual == null){
            System.out.println("Esta version no existe");
            return;
        }

        System.out.println("ID: " + actual.getId());
        System.out.println("Contenido:");
        System.out.println(actual.getContenido());
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        for(Version hijo : actual.getHijos()){
            recorrerVersionTree(hijo);
        }
    }

    public void recorrer(){
        recorrerVersionTree(raiz);
    }

    private Version buscar(Version actual,int id){

        if (actual == null){
            return null;
        }

        if (actual.getId() == id){
            return actual;
        }

        for(Version hijo : actual.getHijos()){
            Version encontrado = buscar(hijo,id);
            if (encontrado != null){
                return encontrado;
            }
        }

        return null;
    }

    public Version buscarVersion(int id_version){
        return buscar(raiz,id_version);

    }

    public void agregarVersion(String contenido){

        Version nueva_version = new Version(contador_id++,contenido,version_actual);

        version_actual.getHijos().add(nueva_version);

        version_actual = nueva_version;
    }

    public void mostrarVersionActual(){
        System.out.println("ID: " + version_actual.getId());
        System.out.println("Contenido:");
        System.out.println(version_actual.getContenido());
    }

    private void mostrarArbolRecursivo(Version version, String prefijo, boolean esUltimo) {
        if (version == null){
            return;
        }

        System.out.print(prefijo);

        if (esUltimo) {
            System.out.print("└── ");
        } else {
            System.out.print("├── ");
        }


        if (version == version_actual) {
            System.out.println("👉 (" + version.getId() + ") " + version.getContenido());
        } else {
            System.out.println("(" + version.getId() + ") " + version.getContenido());
        }


        for (int i = 0; i < version.getHijos().size(); i++) {
            boolean ultimoHijo = (i == version.getHijos().size() - 1);

            String nuevoPrefijo;
            if (esUltimo) {
                nuevoPrefijo = prefijo + "    ";
            } else {
                nuevoPrefijo = prefijo + "│   ";
            }

            mostrarArbolRecursivo(version.getHijos().get(i), nuevoPrefijo, ultimoHijo);
        }
    }

    public void mostrarVersionTree(){
        mostrarArbolRecursivo(raiz,"",true);
    }

    public void eliminarVersion(int id_version){
        Version version = buscarVersion(id_version);

        if(version == null){
            System.out.println("No se encontro la version");
            return;
        }

        if (version == raiz){
            raiz = null;
            return;
        }

        version.getPadre().getHijos().remove(version);
    }

    private Version copiarVersiones(Version actual){
        if (actual == null){
            return null;
        }

        Version nueva_version = actual;

        for(Version hijo : actual.getHijos()){
            nueva_version.getHijos().add(copiarVersiones(hijo));
        }

        return nueva_version;
    }

    public VersionTree obtenerVersionTree(int id){
        Version version = buscarVersion(id);

        VersionTree versionTree = new VersionTree();
        versionTree.raiz = copiarVersiones(version);

        return versionTree;
    }

    public void cambiarVersionActual(int id){
        version_actual = buscarVersion(id);
    }

    public void eliminarVersionBranch(int id){
        Version version = buscarVersion(id);

        if(version == null || version.getPadre() == null){
            System.out.println("No se puede eliminar");
            return;
        }

        version.getPadre().getHijos().remove(version);
    }

    public void mostrarCaminoRaiz(int id){
        Version version = buscarVersion(id);

        while(version != null){
            System.out.println(version.getId() + "-->" + version.getContenido());
            version = version.getPadre();
        }
    }

    public void contarVersiones(){
        int cantidad = contar(raiz);
        System.out.println("Existen " + cantidad + " versiones actualmente");
    }

    private int contar(Version version){
        if(version == null){
            return 0;
        }

        int total = 1;

        for(Version hijo : version.getHijos()){
            total += contar(hijo);
        }

        return total;
    }

}
