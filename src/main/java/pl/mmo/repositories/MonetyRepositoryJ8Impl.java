package pl.mmo.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.mmo.entities.Moneta;
import pl.mmo.entities.Status;


@Service
@Qualifier("lista")
public class MonetyRepositoryJ8Impl implements MonetyRepository {

    private List<Moneta> monety = new ArrayList<Moneta>() {
        {
            add(Moneta.produceMoneta(1L, "Polska", 1L, "zł", "Ładna nowiutka złotóweczka", new Date(), new BigDecimal("1.2"),
                    Status.NOWA));
            add(Moneta.produceMoneta(2L, "Polska", 1L, "zł", "First Moneta", new Date(), new BigDecimal("1.2"),
                    Status.DO_SPRZEDANIA));
            add(Moneta.produceMoneta(3L, "Polska", 1L, "zł", "Second Moneta", new Date(), new BigDecimal("1.2"), Status.DUBLET));
            add(Moneta.produceMoneta(4L, "Polska", 1L, "zł", "Forth Moneta", new Date(), new BigDecimal("1.2"),
                    Status.DO_SPRZEDANIA));
            add(Moneta.produceMoneta(5L, "Polska", 1L, "zł", "Moneta Number 5", new Date(), new BigDecimal("1.2"), Status.NOWA));
            add(Moneta.produceMoneta(6L, "Polska", 1L, "zł", "Sixth Moneta", new Date(), new BigDecimal("1.2"), Status.NOWA));
        }
    };

    @Override
    public List<Moneta> findAll() {
        return this.monety;
    }

    @Override
    public Moneta readById(Long id) throws NoSuchMonetaException {
        return this.monety.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchMonetaException::new);
    }

    @Override
    public Moneta create(Moneta moneta) {
        if (!monety.isEmpty()) {
            moneta.setNumerKatalogowy(
                    this.monety.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            moneta.setNumerKatalogowy(1L);
        }
        this.monety.add(moneta);
        return moneta;
    }

    @Override
    public Moneta update(Moneta moneta) throws NoSuchMonetaException {
        for (int i = 0; i < this.monety.size(); i++) {
            if (Objects.equals(this.monety.get(i).getNumerKatalogowy(), moneta.getNumerKatalogowy())) {
                this.monety.set(i, moneta);
                return moneta;
            }
        }
        throw new NoSuchMonetaException("Nie ma takiej Monety: " + moneta.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchMonetaException {
        for (int i = 0; i < this.monety.size(); i++) {
            if (Objects.equals(this.monety.get(i).getNumerKatalogowy(), id)) {
                this.monety.remove(i);
            }
        }
        throw new NoSuchMonetaException("Nie ma takiej Monety: " + id);
    }

}