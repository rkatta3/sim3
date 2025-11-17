# Sim3 - ALU Implementation

## Overview
This project implements an Arithmetic Logic Unit (ALU) using logical gates and composition of smaller components. You will build three Java classes that model hardware components using only logical operators.

## Purpose
Implement a functional ALU by building from basic components to demonstrate how logical expressions can be encoded as logic gates and composed into complex hardware structures.

## Learning Objectives
- Build hardware components using logical operators
- Understand ALU architecture and two-pass execution
- Practice composition of smaller pieces into larger systems
- Work with bit-level operations without high-level constructs

## Required Files to Submit
1. `Sim3_MUX_8by1.java` - 8-input multiplexer
2. `Sim3_ALUElement.java` - Single-bit ALU element
3. `Sim3_ALU.java` - Complete ALU (any number of bits)

## Critical Limitations

### Operators NOT Allowed
- **No `if()` statements** anywhere (including in MUX!)
- **No comparison operators**: `<`, `<=`, `>`, `>=` (except in `for()` loops)
- **No arithmetic**: Addition/subtraction banned (except for array indexing and copying carry bits)

### Operators Allowed
- All basic Java logical operators: `&&`, `||`, `!`, `&`, `|`, `^`
- Equality operators: `==`, `!=`
- `++` operator (only in `for()` loops)

## Component 1: MUX (Multiplexer)

### Class: Sim3_MUX_8by1

**Inputs:**
- `control[]` - 3-bit array (RussWire objects), element 0 is LSB
- `in[]` - 8-bit array (RussWire objects)

**Output:**
- `out` - Single RussWire (not an array)

**Method:**
- `void execute()` - Selects one of 8 inputs based on 3-bit control value

**Behavior:**
Implements an 8-input MUX where each input is 1 bit wide. Must use only logical operators—no `if()` statements!

**Challenge:** Can you adapt this to work as a 2-input MUX without adding control bits?

## Component 2: ALU Element

### Class: Sim3_ALUElement

**Inputs:**
- `aluOp` - 3 bits, encodes operation:
  - 0 = AND
  - 1 = OR
  - 2 = ADD
  - 3 = LESS
  - 4 = XOR
- `bInvert` - 1 bit
- `a, b` - 1 bit each
- `carryIn` - 1 bit
- `less` - 1 bit (only valid in pass 2)

**Outputs:**
- `result` - 1 bit (final result of operation)
- `addResult` - 1 bit (adder output, always set regardless of operation)
- `carryOut` - 1 bit

### Two-Pass Execution

**Pass 1: `execute_pass1()`**
- All inputs valid **except** `less`
- Must compute adder operation (handling `bInvert`)
- Must set `addResult` and `carryOut`
- **Do NOT** set `result` yet

**Pass 2: `execute_pass2()`**
- All inputs valid (including `less`)
- Must generate final `result` output based on `aluOp`

### Implementation Hint
Start by doing all work in `execute_pass1()`, treating `less` as false. This will pass most testcases. Once working, split into two passes to handle LESS operation correctly.

## Component 3: Complete ALU

### Class: Sim3_ALU

**Constructor Parameter:**
- `X` - Number of bits (must support any size ≥ 2, not just 32)

**Inputs:**
- `aluOp` - 3 bits (see ALU Element)
- `bNegate` - 1 bit
- `a, b` - X bits each

**Output:**
- `result` - X bits

**Method:**
- `void execute()` - Orchestrates two-pass execution across all ALU elements

### Suggested Structure
```java
void execute() {
    // Pass 1: Feed inputs and run adders
    for (int i=0; i<X; i++) {
        // Feed inputs into element[i] (except LESS)
        // Call execute_pass1() on element
    }

    // Set LESS inputs
    // Copy addResult from MSB to LESS input of LSB
    // Set remaining LESS inputs

    // Pass 2: Generate results
    // Call execute_pass2() on every element
    // Copy to result[] output
}
```

## How to Add (Without Addition!)

You have two options:

1. **Reuse FullAdder**: Upload your solution from Sim 2 (which included a FullAdder class) and use it inside each ALUElement
2. **Pure logical operators**: Implement both `sum` and `carryOut` using only boolean operators—can you do it in one line each?

## Utility Classes

Utility classes are provided in the project directory. You may use them **exactly as provided**—do not modify them. If you submit copies to GradeScope, they will be ignored.

## Grading Requirements

### Code Must:
- Use **exact filenames** (case-sensitive)
- Not use any other files unless specified
- Follow spec precisely (no name changes, no edits to provided files)
- Match required behavior exactly

### Testing
- **No public testcases provided** - you must write your own
- Download the grading script `grade_sim3` and test locally
- Grading script uses testcases named `Test_*.java` with matching `.out` files
- Secret testcases may test corner cases not in basic tests

### Sharing
- **DO NOT share code** with classmates
- **DO share testcases** on Discord—testcases are the exception to collaboration rules
- Good testcases may be added to the official set

## Submission
Submit only your three Java files to GradeScope:
- `Sim3_MUX_8by1.java`
- `Sim3_ALUElement.java`
- `Sim3_ALU.java`

Do not submit testcases or utility classes.
