package org.libermundi.recipe.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libermundi.recipe.domain.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitOfMeasureRepositoryIT {
    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByName() throws Exception {
        Optional<UnitOfMeasure> pint = unitOfMeasureRepository.findByName("Pint");
        assertEquals("pint",pint.get().getUnit());
    }

    @Test
    public void findByUnit() throws Exception {
        Optional<UnitOfMeasure> tbps = unitOfMeasureRepository.findByUnit("Tbsp");
        assertEquals("Tablespoon(s)",tbps.get().getName());
    }


}