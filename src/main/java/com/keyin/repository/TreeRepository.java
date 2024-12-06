package com.keyin.repository;

import com.keyin.model.TreeEntity;
import com.keyin.model.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRepository extends JpaRepository<TreeEntity, Long> {


}
