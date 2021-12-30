package com.letscode.starwars.network.dataprovider.mapper;

import com.letscode.starwars.network.dataprovider.entity.RebeldeEntity;
import com.letscode.starwars.network.dataprovider.entity.RebeldeItemEntity;
import com.letscode.starwars.network.dataprovider.entity.RebeldeTraicaoEntity;
import com.letscode.starwars.network.usecase.domain.NegociacaoItemDomain;
import com.letscode.starwars.network.usecase.domain.RebeldeDomain;
import com.letscode.starwars.network.usecase.domain.RebeldeLocalizacaoDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RebeldeRepositoryMapper {

  @Mapping(source = "localizacao.galaxia", target = "galaxia")
  @Mapping(source = "localizacao.latitude", target = "latitude")
  @Mapping(source = "localizacao.longitude", target = "longitude")
  @Mapping(target = "traicoes", ignore = true)
  RebeldeEntity toEntity(RebeldeDomain rebeldeDomain);

  @Mapping(source = "galaxia", target = "localizacao.galaxia")
  @Mapping(source = "latitude", target = "localizacao.latitude")
  @Mapping(source = "longitude", target = "localizacao.longitude")
  @Mapping(source = "traicoes", target = "traicoes", qualifiedByName = "mapTraicoes")
  RebeldeDomain toDomain(RebeldeEntity rebeldeEntity);

  Set<RebeldeItemEntity> toEntity(Set<NegociacaoItemDomain> itensDomain);

  default RebeldeEntity toEntity(final RebeldeEntity rebeldeEntity,
                                 final RebeldeLocalizacaoDomain rebeldeLocalizacaoDomain) {
    rebeldeEntity.setGalaxia(rebeldeLocalizacaoDomain.getGalaxia());
    rebeldeEntity.setLongitude(rebeldeLocalizacaoDomain.getLongitude());
    rebeldeEntity.setLatitude(rebeldeLocalizacaoDomain.getLatitude());
    return rebeldeEntity;
  }

  @Named("mapTraicoes")
  default Set<Long> mapTraicoes(final Set<RebeldeTraicaoEntity> traicoes) {
    return traicoes.stream()
            .map(RebeldeTraicaoEntity::getIdRebeldeReporter)
            .collect(Collectors.toSet());
  }
}
