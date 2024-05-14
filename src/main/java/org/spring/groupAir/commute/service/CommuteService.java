package org.spring.groupAir.commute.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.commute.service.serviceInterface.CommuteServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommuteService implements CommuteServiceInterface {

}
