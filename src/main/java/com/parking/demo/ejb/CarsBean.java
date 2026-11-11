package com.parking.demo.ejb;

import com.parking.demo.common.CarDto;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.parkinglot.entities.Cars;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<CarDto> findAllCars()
    {
        LOG.info("findAllCars");
        try {
            TypedQuery<Cars> typedQuery = entityManager.createQuery("SELECT c FROM Cars c", Cars.class);
            List<Cars> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception ex)
        {
            throw new EJBException(ex);
        }
    }

    private List<CarDto> copyCarsToDto(List<Cars> cars)
    {
        List<CarDto> carDtoList = new ArrayList<>();

        for (Cars car : cars)
        {
            CarDto carDto = new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername()
            );

            carDtoList.add(carDto);
        }

        return carDtoList;
    }
}