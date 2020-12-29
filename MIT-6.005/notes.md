I compiled and tested these exercises on sbt (Scala build tool) on Intellij.
Each exercise has a main method or main object in Scala. 

To compile using the command line tool, go to the directory level where you can find the build.sbt file, and enter the command `sbt`, this will create the sbt shell in your terminal.

Then to compile the files, type `compile`.

To run the files, because the project composes of many execises each with a main class or main method, you need to choose which main class to run. 
To do this, you can open up sbt shell
and enter the command `runMain package-name.Main`
