package com.habit.service.impl;

import com.habit.config.SecurityUtils;
import com.habit.dto.request.GoalUnitRequest;
import com.habit.enums.GoalUnitCategory;
import com.habit.model.GoalUnit;
import com.habit.repository.GoalUnitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoalUnitServiceImplTest {

    @Mock
    GoalUnitRepository repository;

    @InjectMocks
    GoalUnitServiceImpl service;

    @Test
    void shouldCreateGoalUnit() {

        GoalUnitRequest request =
                new GoalUnitRequest(
                        "Cup",
                        "cup",
                        GoalUnitCategory.VOLUME
                );

        when(repository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        var response = service.create(request);

        assertEquals("Cup", response.name());

        verify(repository).save(any());
    }

    @Test
    void shouldGetGoalUnitById() {

        UUID id = UUID.randomUUID();

        GoalUnit unit = GoalUnit.builder()
                .id(id)
                .name("Minute")
                .symbol("min")
                .category(GoalUnitCategory.TIME)
                .build();

        when(repository.findById(id))
                .thenReturn(Optional.of(unit));

        var response = service.getById(id);

        assertEquals("Minute", response.name());
    }
    
    @Test
    void shouldGetGoalUnitByNameAndUser() {
        
        UUID id = UUID.randomUUID();
        
        String name = "Minute";
        String createdBy = "test-user";
        
        GoalUnit unit = GoalUnit.builder()
                .id(id)
                .name(name)
                .symbol("min")
                .category(GoalUnitCategory.TIME)
                .build();
        unit.setCreatedBy(createdBy);
        
        when(repository.findByNameAndCreatedBy(name, createdBy))
                .thenReturn(Optional.of(unit));
        
        try (MockedStatic<SecurityUtils> mockedSecurity =
                     Mockito.mockStatic(SecurityUtils.class)) {
            
            mockedSecurity.when(SecurityUtils::getCurrentUserName)
                    .thenReturn(createdBy);
            
            when(repository.findByNameAndCreatedBy(name, createdBy))
                    .thenReturn(Optional.of(unit));
            
            var response = service.getByNameAndCreator(name);
            
            assertEquals(name, response.getName());
        }
    }
    
    @Test
    void shouldUpdateGoalUnit() {

        UUID id = UUID.randomUUID();

        GoalUnit unit = GoalUnit.builder()
                .id(id)
                .name("Old")
                .build();

        GoalUnitRequest request =
                new GoalUnitRequest(
                        "New",
                        "n",
                        GoalUnitCategory.COUNT
                );

        when(repository.findById(id))
                .thenReturn(Optional.of(unit));

        when(repository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        var response = service.update(id, request);

        assertEquals("New", response.name());
    }

    @Test
    void shouldDeleteCustomGoalUnit() {

        UUID id = UUID.randomUUID();

        GoalUnit unit = GoalUnit.builder()
                .id(id)
                .isSystem(false)
                .build();

        when(repository.findById(id))
                .thenReturn(Optional.of(unit));

        service.delete(id);

        verify(repository).delete(unit);
    }

    @Test
    void shouldNotDeleteSystemGoalUnit() {

        UUID id = UUID.randomUUID();

        GoalUnit unit = GoalUnit.builder()
                .id(id)
                .isSystem(true)
                .build();

        when(repository.findById(id))
                .thenReturn(Optional.of(unit));

        assertThrows(
                IllegalStateException.class,
                () -> service.delete(id)
        );
    }
}