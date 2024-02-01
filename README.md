# Sub-Dictionary and Cell Phones Records Management System

## Overview
This assignment for COMP 249, Fall 2020, is designed to enhance your understanding and skills in File I/O, ArrayLists, and Linked Lists in Java. The primary objectives are to create a sub-dictionary from a text file and to manage cell phone records using linked lists.

## Due Date
- **Sunday, December 4th, 2020 at 11:59 PM**

## Objectives
- To practice file input/output operations.
- To gain experience with ArrayList and Linked Lists.
- To develop programs that can process and organize data efficiently.

## Part 1: ArrayList & File I/O - Sub-Dictionary Creator
### Task
Develop a program that reads any text file and generates a sub-dictionary containing all words based on specific rules.

### Rules for Sub-Dictionary Creation
- Unique entries for each word.
- All words are in UPPERCASE.
- Exclude punctuation and numbers from words.
- Ignore single characters except A and I.
- Sort words alphabetically and group by the first character.

### Technical Requirements
- Use `ArrayList` to store data before outputting to a file.
- The output file should be named `SubDictionary.txt`.
- Only use `ArrayList`, `Scanner`, `PrintWriter`, `FileOutputStream`, `FileInputStream`, and `FileNotFoundException` from Java libraries.

## Part 2: Linked Lists - Cell Phones Records
### Task
Implement a program that uses linked lists to manage a dataset of cell phone records.

### CellPhone Class Requirements
- Attributes: `serialNum`, `brand`, `year`, `price`.
- Implement parameterized and copy constructors, `clone()`, `toString()`, and `equals()` methods.

### CellList Class Requirements
- Inner class `CellNode` with attributes for `CellPhone` object and a pointer to the next `CellNode`.
- Methods for adding, inserting, deleting, and replacing cell phone records in the list.
- Methods for finding and checking the existence of records based on serial numbers.
- Display contents of the list and check equality of two lists.

### CellListUtilization
- Create and populate `CellList` objects from `Cell_Info.txt`.
- Include user interaction for searching serial numbers.
- Test all constructors and methods thoroughly.
