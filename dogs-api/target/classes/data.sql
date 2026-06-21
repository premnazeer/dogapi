INSERT INTO dogs (
    name, breed, supplier, badge_id, gender,
    birth_date, date_acquired, current_status,
    kennelling_characteristic, deleted
) VALUES
('Rex', 'German Shepherd', 'Kent Kennels', 'K9-001', 'MALE',
 '2022-01-10', '2023-01-01', 'IN_SERVICE', 'Calm', false),

('Max', 'Labrador', 'Alpha Kennels', 'K9-002', 'MALE',
 '2021-05-20', '2022-06-01', 'IN_TRAINING', 'Friendly', false),

('Bella', 'Beagle', 'Kent Kennels', 'K9-003', 'FEMALE',
 '2020-03-15', '2021-04-10', 'RETIRED', 'Nervous', false);