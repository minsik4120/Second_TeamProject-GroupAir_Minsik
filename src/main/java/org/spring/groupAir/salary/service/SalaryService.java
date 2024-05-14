package org.spring.groupAir.salary.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.salary.service.serviceInterface.SalaryServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SalaryService implements SalaryServiceInterface {


}
