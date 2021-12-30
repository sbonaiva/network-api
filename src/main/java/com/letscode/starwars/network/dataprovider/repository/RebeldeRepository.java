package com.letscode.starwars.network.dataprovider.repository;

import com.letscode.starwars.network.dataprovider.entity.RebeldeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldeRepository extends JpaRepository<RebeldeEntity, Long> {

  @Query(
          value = "select count (*) " +
                  "from tbl_rebelde reb " +
                  "where not exists ( " +
                  "    select count(*) total_sub " +
                  "    from tbl_rebelde_traicao tra " +
                  "    where reb.id_rebelde = tra.id_rebelde_reportado " +
                  "    group by tra.id_rebelde_reportado " +
                  "    having total_sub >= 3 " +
                  ")",
          nativeQuery = true
  )
  long countRebeldes();

  @Query(
          value = "select count (*) " +
                  "from tbl_rebelde reb " +
                  "where exists ( " +
                  "    select count(*) total_sub " +
                  "    from tbl_rebelde_traicao tra " +
                  "    where reb.id_rebelde = tra.id_rebelde_reportado " +
                  "    group by tra.id_rebelde_reportado " +
                  "    having total_sub >= 3 " +
                  ")",
          nativeQuery = true
  )
  long countTraidores();

  @Query(
          value = "select sum(inv.quantidade) " +
                  "from tbl_rebelde_inventario inv " +
                  "where not exists ( " +
                  "    select count(*) total_sub " +
                  "    from tbl_rebelde_traicao tra " +
                  "    where inv.id_rebelde = tra.id_rebelde_reportado " +
                  "    group by tra.id_rebelde_reportado " +
                  "    having total_sub >= 3 " +
                  ") " +
                  "and item = ?1 " +
                  "group by inv.item ",
          nativeQuery = true
  )
  Integer countRecursosRebeldes(String item);

  @Query(
          value = "select sum(inv.quantidade) " +
                  "from tbl_rebelde_inventario inv " +
                  "where exists ( " +
                  "    select count(*) total_sub " +
                  "    from tbl_rebelde_traicao tra " +
                  "    where inv.id_rebelde = tra.id_rebelde_reportado " +
                  "    group by tra.id_rebelde_reportado " +
                  "    having total_sub >= 3 " +
                  ") " +
                  "and item = ?1 " +
                  "group by inv.item ",
          nativeQuery = true
  )
  Integer countRecursosTraidores(String item);
}
