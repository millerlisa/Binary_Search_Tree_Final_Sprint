package com.keyin.repository;

import com.keyin.model.TreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeNodeRepository extends JpaRepository<TreeEntity, Long> {

    Object save(TreeEntity treeEntity);
}
