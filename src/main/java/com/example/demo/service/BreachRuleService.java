[ERROR] COMPILATION ERROR : 
[INFO] -------------------------------------------------------------
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/BreachRuleController.java:[34,41] cannot find symbol
  symbol:   method getAllRules()
  location: variable service of type com.example.demo.service.BreachRuleService
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/DeliveryRecordController.java:[35,24] cannot find symbol
  symbol:   method getDeliveryRecordsForContract(java.lang.Long)
  location: variable service of type com.example.demo.service.DeliveryRecordService
[INFO] 2 errors 
[INFO] -------------------------------------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  32.296 s
[INFO] Finished at: 2025-12-25T16:52:21Z
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.13.0:compile (default-compile) on project demo: Compilation failure: Compilation failure: 
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/BreachRuleController.java:[34,41] cannot find symbol
[ERROR]   symbol:   method getAllRules()
[ERROR]   location: variable service of type com.example.demo.service.BreachRuleService
[ERROR] /home/coder/Workspace/demo/src/main/java/com/example/demo/controller/DeliveryRecordController.java:[35,24] cannot find symbol
[ERROR]   symbol:   method getDeliveryRecordsForContract(java.lang.Long)
[ERROR]   location: variable service of type com.example.demo.service.DeliveryRecordService
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
coder@9425743721c7:~/Workspace/demo$ 