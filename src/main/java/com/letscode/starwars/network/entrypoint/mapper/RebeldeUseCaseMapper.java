package com.letscode.starwars.network.entrypoint.mapper;

import com.letscode.starwars.network.entrypoint.dto.request.RebeldeLocalizacaoRequestDto;
import com.letscode.starwars.network.entrypoint.dto.request.RebeldeRequestDto;
import com.letscode.starwars.network.entrypoint.dto.response.RebeldeResponseDto;
import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.usecase.domain.RebeldeLocalizacaoDomain;
import com.letscode.starwars.network.usecase.domain.TraidorDomain;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RebeldeUseCaseMapper {

  RebeldeDomain toDomain(RebeldeRequestDto rebeldeRequestDto);

  RebeldeLocalizacaoDomain toDomain(RebeldeLocalizacaoRequestDto rebeldeLocalizacaoRequestDto);

  TraidorDomain toDomain(Long idReporter, Long idReportado);

  RebeldeResponseDto toDto(RebeldeDomain rebeldeDomain);

}
