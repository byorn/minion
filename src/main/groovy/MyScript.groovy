//def oCRScannerDemo = new OCRScannerDemo();
//oCRScannerDemo.loadTrainingImages("C:\\work\\");
//oCRScannerDemo.process("C:\\work\\phototest.jpg");


//def pattern = ~/^[A-Z](.+)[A-Z]{1,}+(.+)/
//
//println  "*********************************" + pattern.matcher("TaskType").matches()   

String s = "Blah"
println s.replaceFirst (s.substring(0,1), s.substring(0,1).toLowerCase())

