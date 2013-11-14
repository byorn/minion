//class MyScript{
//    def static doSomething(){
//        println "ok"
//    }
//}

//def sql = Sql.newInstance('jdbc:mysql://localhost:3306/sambol', 'root', '', 'com.mysql.jdbc.Driver')
//sql.eachRow('select * from user'){ row ->
//  //  println row[0]
//}

println "MyScript run"

def myclosure = {
    
    str -> 
    
    str.replace("name","byorn")
    
    return str.replace("name","byorn")
    
}


println myclosure("name of sfdsfsaf");


myclosure  