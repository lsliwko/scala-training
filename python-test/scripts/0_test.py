# Making a get request
import requests

response = requests.get('https://jsonmock.hackerrank.com/api/medical_records')
data = response.json()
print(str(data))

diagnosisName = 'Pulmonary embolism'
doctorId = 2

pulses = []
for pageNo in range(1, data.get('total_pages')+1):
    url = f'https://jsonmock.hackerrank.com/api/medical_records?page={pageNo}'
    response = requests.get(url)
    data = response.json()

    medical_datas = data.get('data')
    print(f'medical_datas: {medical_datas}')

    for medical_data in medical_datas:
        diagnosis = medical_data.get('diagnosis')
        doctor = medical_data.get('doctor')
        vitals = medical_data.get('vitals')
        if (diagnosis.get('name') == diagnosisName) and (doctor.get('id') == doctorId):
            pulses.append(vitals.get('pulse'))
            print(f'Pulses: {pulses}')


print(f'{sum(pulses) / len(pulses)}')



# pulses = []
# for pageNo in range(1, data.get('total_pages')+1):
#     response = requests.get(f'https://api.github.com?page={pageNo}')
#     data = response.json()
#     # print(str(data))
#     for diagnosis in data.get('diagnosis'):
#         if diagnosis.get('doctor').get('id') == doctorId:
#             if diagnosis.get('name') == diagnosisName:
#                 pulses.append(diagnosis.get('pulse'))



