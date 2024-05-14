package org.spring.groupAir.airplane.service;

import lombok.RequiredArgsConstructor;
import org.spring.groupAir.airplane.service.serviceInterface.AirPlaneServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AirplaneService implements AirPlaneServiceInterface {
}
