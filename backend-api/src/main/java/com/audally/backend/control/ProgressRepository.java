package com.audally.backend.control;

import com.audally.backend.entity.Progress;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ProgressRepository implements PanacheRepositoryBase<Progress,Long> {


}
