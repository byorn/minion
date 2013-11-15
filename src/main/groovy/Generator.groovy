class Generator {
    
    def static generateFile(Closure lineReplacer,  template_dao,file_to_create)
    {
        
        
            def targetFile = new File(file_to_create)
            if( targetFile.exists()){
                targetFile.delete()
                targetFile.createNewFile()
            }


            def templateFile = new File(template_dao)

            templateFile.eachLine{
                    line ->
                    def str =  lineReplacer(line)
                    targetFile.append(str + "\n")
            }
        
    }
    
}