package pl.mmo.entities;


public enum Status {
    
    NOWA("Nowa"), 
    DO_SPRZEDANIA("Do sprzedania"),
    DUBLET("Dublet");
    
    
    public static final Status[] ALL = { NOWA, DO_SPRZEDANIA, DUBLET };
    
    
    private final String name;

    private Status(final String name) {
    	this.name = name;
    }
    
//    public static Status forName(final String name) {
//        if (name == null) {
//            throw new IllegalArgumentException("Nie mozna nula dla Status");
//        }
//        if (name.equalsIgnoreCase("NOWA")) {
//            return NOWA;
//        } else if (name.equalsIgnoreCase("DO_SPRZEDANIA")) {
//            return Status.DO_SPRZEDANIA;
//        }
//        throw new IllegalArgumentException("Nazwa \"" + name + "\" nie pasuje do zadengo Statusu");
//    }
//    
    
    public String getName() {
        return this.name;
    }
}