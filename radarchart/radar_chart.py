#-----Task Description-----------------------------------------------#
#
#  RADAR CHART
#
#  Radar charts are a common way of visualising data comprising
#  several values of different types.  In this task you and your
#  programming partner will develop a Python function that uses
#  Turtle graphics to draw a radar chart given a list of data
#  values.
#   @author:Tensor
#   aadeshnpn.com.np
#--------------------------------------------------------------------#

import turtle
#Function that draws the spider Plot 
def radar_chart(data):
    # Some "typical" test data
    #print "Hello"
    length=len(data) # stores the length of the data provided
    turtle.home()   # Sets the turtle to position (0,0)
    division=360/length #what angle is needed for invidual lines
    poslist=[] #list to store current position
    valpos=[]   #list to store position
    j=0
    turtle.hideturtle() #hides the arrow
        #Draw the foundation of the Radar Chart
    for i in range(length): # Loop until all the given data is plotted
        turtle.forward(200) #move turtle forward
        turtle.dot(10,"black") # Draw the black dot at the end of each data
        nowpos=turtle.pos() # store the current position
        poslist.append(nowpos) #append the current position to list
        #turtle.hideturtle()
        turtle.setpos(nowpos[0]+10,nowpos[1]) #get the turtle to new postion to write data
        turtle.write(data[i], True, align="center") # Write the label of data
        turtle.setpos(nowpos[0],nowpos[1]) #return to the previous position
        turtle.back(200) #return home
        turtle.left(division) # rotate by the specific angle
    turtle.home()    # return to turtle home
    #Connect the ends points of the radar chart
    for i in poslist: #
        turtle.setpos(i[0],i[1])
        #turtle.setpos(i[j],i[j+1])
        #turtle.forward(100)
        #turtle.home()
        #turtle.degree(division)
        #turtle.heading()
        #turtle.forward(100)
    turtle.setpos(poslist[0][0],poslist[0][1])
    turtle.home()
    #Draw green Dots 
    for i in range(length):
        incval=data[i]
        turtle.forward(incval*2)
        turtle.dot(15,"green")
        nowpos=turtle.pos()
        valpos.append(nowpos) 
        turtle.back(incval*2)
        turtle.left(division)
    turtle.begin_poly()
    turtle.fill(True)
    #Fill the green Dots
    for i in valpos:
        turtle.setpos(int(i[0]),int(i[1]))
    turtle.setpos(valpos[0][0],valpos[0][1])
    turtle.end_poly()
    p = turtle.get_poly()
    turtle.register_shape("jpt", p)
    turtle.color("Green", "Green")
    turtle.begin_fill()
    #turtle.p(80)
    turtle.end_fill()
    turtle.fill(False)


#
#--------------------------------------------------------------------#



#-----Testing--------------------------------------------------------#
#
#  Below is some data to use when testing your function
#

# Some "typical" test data
test1 = [32, 56, 100, 78, 23, 90, 0, 12, 45, 90, 23]
test2 = [88, 76, 63, 84, 98, 10, 17, 82, 65]
test3 = [32, 61, 15, 95, 24]
test4 = [60, 97, 86, 39, 67, 22, 62, 58, 20, 37, 65,
         72, 34, 67, 64, 56, 50, 32, 50, 93, 68]

# Some "extreme" test data
test5 = [25, 75, 25] # a triangle (the shortest "reasonable" list) 
test6 = [100, 100, 100, 100] # produces a filled square

# Run a test - change the test number for different charts
turtle.speed('slow') # draw quickly
radar_chart(test4) # call the function

#
#--------------------------------------------------------------------#


