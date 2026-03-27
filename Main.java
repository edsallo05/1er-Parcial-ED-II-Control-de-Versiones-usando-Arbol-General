public class Main {
    public static void main(String[] args){

        VersionTree versionTree = new VersionTree("Primera Version");

        versionTree.agregarVersion("Segunda Version");
        versionTree.cambiarVersionActual(1);
        versionTree.agregarVersion("Tercera Version");

        versionTree.cambiarVersionActual(2);
        versionTree.agregarVersion("Cuarta Version");
        versionTree.agregarVersion("Quinta Version");

        versionTree.cambiarVersionActual(3);
        versionTree.agregarVersion("Sexta Version");

        versionTree.mostrarVersionTree();
    }
}
