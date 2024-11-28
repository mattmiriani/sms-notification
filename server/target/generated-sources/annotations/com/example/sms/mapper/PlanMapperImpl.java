package com.example.sms.mapper;

import com.example.sms.dto.PlanDTO;
import com.example.sms.entity.Plan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T23:02:55-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class PlanMapperImpl implements PlanMapper {

    @Override
    public PlanDTO planToPlanDTO(Plan plan) {
        if ( plan == null ) {
            return null;
        }

        PlanDTO planDTO = new PlanDTO();

        planDTO.setActive( plan.getActive() );
        planDTO.setCreatedAt( plan.getCreatedAt() );
        planDTO.setCredit( plan.getCredit() );
        planDTO.setId( plan.getId() );
        planDTO.setMessageAmount( plan.getMessageAmount() );
        planDTO.setType( plan.getType() );
        planDTO.setUpdatedAt( plan.getUpdatedAt() );

        return planDTO;
    }

    @Override
    public Plan planDTOToPlan(PlanDTO planDTO) {
        if ( planDTO == null ) {
            return null;
        }

        Plan plan = new Plan();

        plan.setActive( planDTO.getActive() );
        plan.setCreatedAt( planDTO.getCreatedAt() );
        plan.setCredit( planDTO.getCredit() );
        plan.setId( planDTO.getId() );
        plan.setMessageAmount( planDTO.getMessageAmount() );
        plan.setType( planDTO.getType() );
        plan.setUpdatedAt( planDTO.getUpdatedAt() );

        return plan;
    }
}
