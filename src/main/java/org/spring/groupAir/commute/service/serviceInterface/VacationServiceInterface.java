package org.spring.groupAir.commute.service.serviceInterface;

import org.spring.groupAir.commute.dto.VacationDto;

public interface VacationServiceInterface {

    void vacationCreate(VacationDto vacationDto);

    int vacationPeople();

    int sickVacationPeople();

    void findVacationPerson();

    void deleteOverTimeVacation();
}
