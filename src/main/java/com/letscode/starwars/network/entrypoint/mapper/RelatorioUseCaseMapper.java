package com.letscode.starwars.network.entrypoint.mapper;

import com.letscode.starwars.network.entrypoint.dto.response.RelatorioResponseDto;
import com.letscode.starwars.network.usecase.domain.RelatorioDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RelatorioUseCaseMapper {

  RelatorioResponseDto toDto(RelatorioDomain relatorioDomain);

}
