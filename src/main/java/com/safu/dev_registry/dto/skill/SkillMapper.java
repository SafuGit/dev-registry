package com.safu.dev_registry.dto.skill;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.safu.dev_registry.models.Skill;

@Mapper(componentModel = "spring")
public interface SkillMapper {
  @Mapping(target = "userId", source = "user.id")
  SkillResponse toSkillResponse(Skill skill);

  List<SkillResponse> toSkillResponseList(List<Skill> skills);
}
