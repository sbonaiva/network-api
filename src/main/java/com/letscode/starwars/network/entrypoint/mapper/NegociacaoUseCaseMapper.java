package com.letscode.starwars.network.entrypoint.mapper;

import com.letscode.starwars.network.entrypoint.dto.request.NegociacaoRequestDto;
import com.letscode.starwars.network.usecase.domain.NegociacaoDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NegociacaoUseCaseMapper {

  NegociacaoDomain toDomain(NegociacaoRequestDto negociacaoRequestDto);

}
