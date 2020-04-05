# History
This program started in the summer of 2017.

### Inspiration
[@eidolon1138](https://github.com/eidolon1138) is the one who originally came up with the idea to collect data from his Outback Mate device. He helped
set up the database and [@retrodaredevil](https://github.com/retrodaredevil) did the rest. Eventually [@retrodaredevil](https://github.com/retrodaredevil) created an android app making it much
more convenient than a website.

[@retrodaredevil](https://github.com/retrodaredevil) came up with the idea of the outhouse status when he walked all the way out to the outhouse only to find
that it was occupied! He walked all the way back inside, then went back out a few minutes later. He knew that something
had to be done about this first world problem.


#### 2017
* A perl script was created in a single day to collect data from an Outback Mate serial port
* The terrible perl script was ditched to start on the Java program. The program allowed packets to be added to a CouchDB database
#### 2018
* This continued in the summer of 2018. The formatting of the packets was completely rethought. The web application
was created and completed in less than a week.
* An Android app was created to see the data continuously updated in a status notification
#### 2019
* Outhouse status was added
* Renogy rover support was added
* To maintain compatibility with the previous packet structure, Source and Fragment packets types were added to
have the ability to have multiple instances uploading packets to a single database
* InfluxDB support was added allowing for easy configuration of a Grafana dashboard
* Raspberry Pi running the outhouse program didn't survive the freezing temperatures (RIP outhousepi 2019-2019)
#### 2020
* PVOutput was setup
* Outhouse code was completely removed from SolarThing codebase
* "Events Display" was added to the Android application
* RoverPi became corrupt, but was eventually reflashed and set up again


### Moving from Gson to Jackson
This project started out with Gson, but as of 2019.12.24, I have started to move to Jackson. I originally chose Gson for its
simplicity. It has served this project very well and is very user friendly. However, I got tired of writing custom
deserializing functions to deserialize advanced packets. Jackson is very annotation orientated and is very
feature rich. The added complexity of Jackson is worth the speed of development it brings.

### Configuration
When developing SolarThing, I didn't want to hard code values everywhere in the code, so I decided to
go with command line arguments. For this, I decided to use [JCommander](https://github.com/cbeust/jcommander).

JCommander was a great option until I wanted to use inheritance to define which types of programs can have
certain options. JCommander did not work with interfaces an [JewelCli](http://jewelcli.lexicalscope.com/) did. JewelCli
is like the Retrofit of command line parsers. Defining options in interfaces gives you many options for how to structure
your configuration. If SolarThing or another one of my projects needs command line parsing again, JewelCli will be my go to library.

At this point, the command line arguments were pretty crazy. Plus, swapping out different configs meant changing the
file that actually ran the `java -jar` command. I knew it was time to move to JSON configuration. This allowed for a lot of
flexibility. While GSON was used to start with, the JSON configuration code was one of the reasons I felt like I needed to rewrite a lot
of the stuff that used JSON. I wasn't utilizing Gson's deserialization features, so I decided to switch
to Jackson altogether as explained above.

Currently the configuration is very easy to change. I can swap out what configuration I'm using easily and can
use the same CouchDB or InfluxDB configuration on multiple devices running SolarThing.

### Legacy
[The perl script](../legacy/helloworld.pl) is a legacy program. It was the program that started solarthing.
After learning perl for a day. I went straight back to Java, which I am more familiar with.
