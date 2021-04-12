package com.samsungsds.eshop.ads;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends CrudRepository<Ad, Integer> {
  Iterable<Ad> findByCategoryIn(String[] categories);
}
