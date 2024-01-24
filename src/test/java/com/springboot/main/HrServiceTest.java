package com.springboot.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.main.exception.InvalidIdException;

import com.springboot.main.model.Hr;

import com.springboot.main.repository.HrRepository;

import com.springboot.main.service.HrService;

@SpringBootTest
public class HrServiceTest {
	@Mock
	private HrRepository hrRepository;

	@InjectMocks
	private HrService hrService;

	@Test
	public void testGetOneValidHrId() throws InvalidIdException {

		int hrId = 254;
		Hr hr = new Hr();
		when(hrRepository.findById(hrId)).thenReturn(Optional.of(hr));

		Hr result = hrService.getOne(hrId);

		assertEquals(hr, result);
	}

}
