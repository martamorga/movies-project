package pl.mmo.entities;


public enum Status {

    WYPOZYCZONY("Film wypożyczony"),
    DO_WYPOZYCZENIA("Film do wypożyczenia"),
    KILKA_DOSTEPNYCH_SZTUK("Kilka dostępnych sztuk filmów");


    public static final Status[] ALL = {WYPOZYCZONY, DO_WYPOZYCZENIA, KILKA_DOSTEPNYCH_SZTUK};


    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}