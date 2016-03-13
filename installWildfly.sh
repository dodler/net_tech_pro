sudo -s
cd /opt

adduser --no-create-home --disabled-password --disabled-login wildfly

wget http://download.jboss.org/wildfly/8.2.0.Final/wildfly-8.2.0.Final.tar.gz

tar -xzvf wildfly-8.2.0.Final.tar.gz

ln -s wildfly-8.2.0.Final wildfly

chown -R wildfly.wildfly wildfly
chown -R wildfly.wildfly wildfly-8.2.0.Final

cd /opt/wildfly/bin/init.d

#instruction here
#https://gesker.wordpress.com/2015/02/17/quick-install-wildfly-8-2-0-on-ubuntu-14-04/
#next - change wildfly.conf as shown in commentary

#Uncomment and Edit the following lines:
#JBOSS_HOME="/opt/wildfly"

#JBOSS_USER=wildfly

#JBOSS_MODE=standalone

#JBOSS_CONFIG=standalone-full.xml

#STARTUP_WAIT=120

#SHUTDOWN_WAIT=120

#JBOSS_CONSOLE_LOG="/var/log/wildfly/console.log"

# save changes and continue

cd /etc/default
ln -s /opt/wildfly/bin/init.d/wildfly.conf wildfly

cd /etc/init.d
ln -s /opt/wildfly/bin/init.d/wildfly-init-debian.sh wildfly

#than i can start wildfly as service

#service wildfly start
# no autostart is done
# need manual start for each pc turn on
