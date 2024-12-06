package com.keyin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trees")
@Data
public class TreeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputNumbers;

    @Column(columnDefinition = "TEXT")
    private String treeStructure;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getInputNumbers() {return inputNumbers;}
    public void setInputNumbers(String inputNumbers) {this.inputNumbers = inputNumbers;}

    public String getTreeStructure() {return treeStructure;}
    public void setTreeStructure(String treeStructure) {this.treeStructure = treeStructure;}

}
