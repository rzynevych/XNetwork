import random

print('use znetwork;')
# for i in range (0, 10):
#     print("INSERT INTO messages (number, parent_id, receiver, text, username) VALUES ")
#     for i in range (0, 10000):
#         print("({},4,6,'Message with some text number: {}','example'),".format(random.randint(0, 10000), i))
#     print("({},4,6,'Message with some text number: {}','example');".format(random.randint(0, 10000), i))
# for i in range (0, 10):
#     print("INSERT INTO messages (number, parent_id, receiver, text, username) VALUES ")
#     for i in range (0, 10000):
#         print("({},5,9,'Message with some text number: {}','X-MAN'),".format(random.randint(0, 10000), i))
#     print("({},5,9,'Message with some text number: {}','X-MAN');".format(random.randint(0, 10000), i))
# for i in range (0, 10):
#     print("INSERT INTO messages (number, parent_id, receiver, text, username) VALUES ")
#     for i in range (0, 10000):
#         print("({},9,7,'Message with some text number: {}','Cool User'),".format(random.randint(0, 10000), i))
#     print("({},9,7,'Message with some text number: {}','Cool User');".format(random.randint(0, 10000), i))
for i in range (0, 10):
    print("INSERT INTO messages (number, parent_id, receiver, text, username) VALUES ")
    for i in range (0, 10000):
        print("({},7,5,'Message with some text number: {}','ex\\'123'),".format(random.randint(0, 10000), i))
    print("({},7,5,'Message with some text number: {}','ex\\'123');".format(random.randint(0, 10000), i))
for i in range (0, 10):
    print("INSERT INTO messages (number, parent_id, receiver, text, username) VALUES ")
    for i in range (0, 10000):
        print("({},6,1,'Message with some text number: {}','Ilya'),".format(random.randint(0, 10000), i))
    print("({},6,1,'Message with some text number: {}','Ilya');".format(random.randint(0, 10000), i))
for i in range (0, 10):
    print("INSERT INTO messages (number, parent_id, receiver, text, username) VALUES ")
    for i in range (0, 10000):
        print("({},1,4,'Message with some text number: {}','Ruslan'),".format(random.randint(0, 10000), i))
    print("({},1,4,'Message with some text number: {}','Ruslan');".format(random.randint(0, 10000), i))