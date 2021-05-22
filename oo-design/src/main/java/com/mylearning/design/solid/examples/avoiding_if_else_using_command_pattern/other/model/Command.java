package com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Command {

    String name;
    List<String> params;
}
