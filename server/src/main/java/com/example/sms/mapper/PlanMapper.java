package com.example.sms.mapper;

import com.example.sms.dto.PlanDTO;
import com.example.sms.entity.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlanMapper {

    PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);

    PlanDTO planToPlanDTO(Plan plan);

    Plan planDTOToPlan(PlanDTO planDTO);
}
