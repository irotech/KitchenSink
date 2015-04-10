# RabbitMQ notes

Installation

Windows
	* Download and install Erlang (www.erlang.org/download/otp_win64_17.4.exe)
	* Donwload and install Rabbitmq-server (www.rabbitmq.com/releases/rabbitmq-server/v3.5.0/rabbitmq-server-3.5.0.exe)
	* Run, as administrator, the command \rabbitmq_server-3.5.0\sbin>rabbitmq-plugins.bat enable rabbitmq_management
		The following plugins have been enabled:
		mochiweb
		webmachine
		rabbitmq_web_dispatch
		amqp_client
		rabbitmq_management_agent
		rabbitmq_management
	* Try to access into the console http://localhost:15672/ 
		user:	guest
		pass:	guest
	* <path>\rabbitmq_server-3.5.0\sbin>rabbitmqctl.bat start_app
	* <path>\rabbitmq_server-3.5.0\sbin>rabbitmqctl.bat status
	* <path>\rabbitmq_server-3.5.0\sbin>rabbitmq-server.bat start
    				RabbitMQ 3.5.0. Copyright (C) 2007-2014 GoPivotal, Inc.
 		##  ##      Licensed under the MPL.  See http://www.rabbitmq.com/
  		##  ##
  		##########  
  		######  ##
  		##########
              		Starting broker... completed with 6 plugins.
    * Reset the server (needs the service started)
    	<path>\rabbitmq_server-3.5.0\sbin>rabbitmqctl.bat stop_app
    	<path>\rabbitmq_server-3.5.0\sbin>rabbitmqctl.bat reset
    	<path>\rabbitmq_server-3.5.0\sbin>rabbitmqctl.bat start_app

Linux

OsX