SimpleToastModule
-------------------------------------
Created by Arnav Sankaran  
Thanks to @JacisNonsense for his work on Toast

SimpleToastModule is a thin layer on top on Toast. Extending from SimpleToastModule will allowing you to program your simulated robot using the usual robotInit(), autonomousPeriodic(), teleopInit(), etc. functions without needing to worry about implementing the StateTrackers and StateTransitions which would be required to implement if extending from the default ToastModule.