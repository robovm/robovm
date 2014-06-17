#!/bin/bash -
# Copyright (C) 2012 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

set -o nounset                              # Treat unset variables as an error
set -e

DIR=$(dirname $0)

openssl req -config ${DIR}/default.cnf -new -nodes -batch > /tmp/cert-rsa-req.pem
openssl req -in /tmp/cert-rsa-req.pem -pubkey -noout | openssl rsa -pubin -pubout -outform der > ${DIR}/cert-rsa-pubkey.der
openssl x509 -extfile ${DIR}/default.cnf -days 3650 -extensions usr_cert -req -signkey /tmp/privkey.pem -outform d < /tmp/cert-rsa-req.pem > ${DIR}/cert-rsa.der
rm /tmp/cert-rsa-req.pem

openssl asn1parse -in ${DIR}/cert-rsa.der -inform d -out ${DIR}/cert-rsa-tbs.der -noout -strparse 4
SIG_OFFSET=$(openssl asn1parse -in ${DIR}/cert-rsa.der -inform d | tail -1 | cut -f1 -d:)
openssl asn1parse -in ${DIR}/cert-rsa.der -inform d -strparse ${SIG_OFFSET} -noout -out ${DIR}/cert-rsa-sig.der

# extract startdate and enddate
openssl x509 -in ${DIR}/cert-rsa.der -inform d -noout -startdate -enddate > ${DIR}/cert-rsa-dates.txt

# extract serial
openssl x509 -in ${DIR}/cert-rsa.der -inform d -noout -serial > ${DIR}/cert-rsa-serial.txt

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions keyUsage_extraLong_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-keyUsage-extraLong.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions extendedKeyUsage_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-extendedKeyUsage.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions ca_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-ca.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions userWithPathLen_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-userWithPathLen.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions caWithPathLen_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-caWithPathLen.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions alt_other_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-alt-other.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions alt_email_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-alt-email.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions alt_dns_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-alt-dns.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions alt_dirname_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-alt-dirname.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions alt_uri_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-alt-uri.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions alt_rid_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-alt-rid.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions alt_none_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-alt-none.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions ipv6_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-ipv6.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions unsupported_cert -req -signkey /tmp/privkey.pem -outform d > ${DIR}/cert-unsupported.der

openssl req -config ${DIR}/default.cnf -new -nodes -batch -config ${DIR}/default.cnf -extensions usr_cert -x509 -sigopt rsa_padding_mode:pss -sigopt rsa_pss_saltlen:1 -outform d > ${DIR}/cert-sigopt.der

openssl dsaparam -out /tmp/dsaparam.pem 1024
openssl req -config ${DIR}/default.cnf -newkey dsa:/tmp/dsaparam.pem -keyout /tmp/dsapriv.pem -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions keyUsage_cert -req -signkey /tmp/dsapriv.pem -outform d > ${DIR}/cert-dsa.der
rm /tmp/dsaparam.pem

openssl ecparam -name sect283k1 -out /tmp/ecparam.pem
openssl req -config ${DIR}/default.cnf -newkey ec:/tmp/ecparam.pem -keyout /tmp/ecpriv.pem -nodes -batch | openssl x509 -extfile ${DIR}/default.cnf -extensions keyUsage_critical_cert -req -signkey /tmp/ecpriv.pem -outform d > ${DIR}/cert-ec.der
rm /tmp/ecparam.pem

# Create temporary CA for CRL generation
rm -rf /tmp/ca
mkdir -p /tmp/ca
touch /tmp/ca/index.txt
touch /tmp/ca/index.txt.attr
echo "01" > /tmp/ca/serial
openssl req -new -nodes -batch -x509 -extensions v3_ca -keyout /tmp/cakey.pem -out /tmp/cacert.pem -days 3650 -config ${DIR}/default.cnf
openssl x509 -in /tmp/cacert.pem -outform d > ${DIR}/cert-crl-ca.der

openssl ca -gencrl -crlhours 70 -keyfile /tmp/cakey.pem -cert /tmp/cacert.pem -out /tmp/crl-empty.pem -config ${DIR}/default.cnf
openssl crl -in /tmp/crl-empty.pem -outform d -out ${DIR}/crl-empty.der

openssl x509 -inform d -in ${DIR}/cert-rsa.der -out /tmp/cert-rsa.pem
openssl ca -revoke /tmp/cert-rsa.pem -keyfile /tmp/cakey.pem -cert /tmp/cacert.pem -config ${DIR}/default.cnf
openssl ca -gencrl -crlhours 70 -keyfile /tmp/cakey.pem -cert /tmp/cacert.pem -out /tmp/crl-rsa.pem -config ${DIR}/default.cnf
openssl crl -in /tmp/crl-rsa.pem -outform d -out ${DIR}/crl-rsa.der

openssl asn1parse -in ${DIR}/crl-rsa.der -inform d -out ${DIR}/crl-rsa-tbs.der -noout -strparse 4
SIG_OFFSET=$(openssl asn1parse -in ${DIR}/crl-rsa.der -inform d | tail -1 | cut -f1 -d:)
openssl asn1parse -in ${DIR}/crl-rsa.der -inform d -strparse ${SIG_OFFSET} -noout -out ${DIR}/crl-rsa-sig.der

openssl x509 -inform d -in ${DIR}/cert-dsa.der -out /tmp/cert-dsa.pem
openssl ca -revoke /tmp/cert-dsa.pem -keyfile /tmp/cakey.pem -cert /tmp/cacert.pem -crl_reason cessationOfOperation -extensions unsupported_cert -config ${DIR}/default.cnf
openssl ca -gencrl -crldays 30 -keyfile /tmp/cakey.pem -cert /tmp/cacert.pem -out /tmp/crl-rsa-dsa.pem -config ${DIR}/default.cnf
openssl ca -gencrl -crldays 30 -keyfile /tmp/cakey.pem -cert /tmp/cacert.pem -out ${DIR}/crl-rsa-dsa-sigopt.pem -config ${DIR}/default.cnf -sigopt rsa_padding_mode:pss -sigopt rsa_pss_saltlen:1
openssl crl -in /tmp/crl-rsa-dsa.pem -outform d -out ${DIR}/crl-rsa-dsa.der
openssl crl -in ${DIR}/crl-rsa-dsa-sigopt.pem -outform d -out ${DIR}/crl-rsa-dsa-sigopt.der

# Unsupported extensions
openssl ca -gencrl -crlexts unsupported_cert -keyfile /tmp/cakey.pem -cert /tmp/cacert.pem -out /tmp/crl-unsupported.pem -config ${DIR}/default.cnf
openssl crl -in /tmp/crl-unsupported.pem -outform d -out ${DIR}/crl-unsupported.der

openssl crl -inform d -in ${DIR}/crl-rsa.der -noout -lastupdate -nextupdate > ${DIR}/crl-rsa-dates.txt
openssl crl -inform d -in ${DIR}/crl-rsa-dsa.der -noout -lastupdate -nextupdate > ${DIR}/crl-rsa-dsa-dates.txt

rm /tmp/cert-rsa.pem /tmp/cert-dsa.pem /tmp/cacert.pem /tmp/cakey.pem /tmp/crl-rsa.pem /tmp/crl-rsa-dsa.pem /tmp/crl-unsupported.pem /tmp/crl-empty.pem
rm -r /tmp/ca

rm /tmp/privkey.pem
rm /tmp/dsapriv.pem
rm /tmp/ecpriv.pem

cat ${DIR}/cert-rsa.der ${DIR}/cert-dsa.der > /tmp/certs.der
openssl x509 -inform d -in ${DIR}/cert-rsa.der > /tmp/certs.pem
openssl x509 -inform d -in ${DIR}/cert-dsa.der >> /tmp/certs.pem

openssl crl2pkcs7 -certfile /tmp/certs.pem -nocrl > ${DIR}/certs-pk7.pem
openssl crl2pkcs7 -certfile /tmp/certs.pem -nocrl -outform d > ${DIR}/certs-pk7.der

rm /tmp/certs.pem
