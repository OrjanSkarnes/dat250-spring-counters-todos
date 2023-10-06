# DAT250: Software Technology Experiment Assignment 6

## Introduction
This report outlines the work done in Experiment Assignment 6, focusing on front-end technologies for enterprise applications using Angular. The assignment consisted of two main experiments:

1. Creating a Single Page Application (SPA) to implement a Todo list using Angular.
2. Enhancing the application using a component library (Angular Material).

## Technical Problems Encountered

### Problem 1: ID Parsing

#### Issue
The ID parsed from the HTTP response in Angular was getting rounded off. This happened because the original ID exceeded JavaScript's highest exact integral value that can be represented, which is `Number.MAX_SAFE_INTEGER`.

#### Solution
Changed the length of the ID
